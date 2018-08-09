/*
 *
 *   Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.carbon.apimgt.core.dao.impl;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.dao.StreamDAO;
import org.wso2.carbon.apimgt.core.exception.APIMgtDAOException;
import org.wso2.carbon.apimgt.core.exception.ExceptionCodes;
import org.wso2.carbon.apimgt.core.streams.EventStream;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the StreamDAO interface. Uses SQL syntax that is common to H2 and MySQL DBs.
 * Hence is considered as the default due to its re-usability.
 */
public class StreamDAOImpl implements StreamDAO {

    private final ApiDAOVendorSpecificStatements sqlStatements;

    private static final String AM_STREAM_TABLE_NAME = "AM_STREAM";
    private static final Logger log = LoggerFactory.getLogger(StreamDAOImpl.class);

    private static final String STREAM_INSERT = "INSERT INTO AM_STREAM (UUID, PROVIDER, NAME, VERSION, DESCRIPTION," +
            "VISIBILITY, LIFECYCLE_STATUS, ENDPOINT, STREAM_TYPE, STREAM_AUTHORIZATION, IS_PRODUCABLE, " +
            "CAN_PRODUCER_ACCESS_DIRECTLY, CAN_PRODUCER_ACCESS_VIA_GATEWAY, PRODUCER_AUTHORIZATION, PRODUCER_TRANSPORT, " +
            "PRODUCER_MESSAGE_TYPE, IS_CONSUMABLE, CAN_CONSUMER_ACCESS_DIRECTLY, CAN_CONSUMER_ACCESS_VIA_GATEWAY, " +
            "CONSUMER_AUTHORIZATION, CONSUMER_TRANSPORT, CONSUMER_DISPLAY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?)";

    private static final String STREAM_SELECT = "SELECT UUID, PROVIDER, NAME, VERSION, DESCRIPTION, " +
            "VISIBILITY, LIFECYCLE_STATUS, ENDPOINT, STREAM_TYPE, STREAM_AUTHORIZATION, IS_PRODUCABLE, " +
            "CAN_PRODUCER_ACCESS_DIRECTLY, CAN_PRODUCER_ACCESS_VIA_GATEWAY, PRODUCER_AUTHORIZATION, PRODUCER_TRANSPORT," +
            "PRODUCER_MESSAGE_TYPE, IS_CONSUMABLE, CAN_CONSUMER_ACCESS_DIRECTLY, CAN_CONSUMER_ACCESS_VIA_GATEWAY, " +
            "CONSUMER_AUTHORIZATION, CONSUMER_TRANSPORT, CONSUMER_DISPLAY FROM AM_STREAM";

    private static final String STREAM_SUMMARY_SELECT = "SELECT UUID, PROVIDER, NAME, VERSION, LIFECYCLE_STATUS FROM AM_STREAM";

    public StreamDAOImpl(ApiDAOVendorSpecificStatements sqlStatements) {
        this.sqlStatements = sqlStatements;
    }


    @Override
    public boolean isStreamExists(String streamID) throws APIMgtDAOException {
        final String query = "SELECT 1 FROM AM_STREAM WHERE UUID = ?";
        try (Connection connection = DAOUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, streamID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public EventStream getStream(String streamID) throws APIMgtDAOException {
        return null;
    }

    @Override
    public boolean isStreamNameExists(String streamName, String providerName) throws APIMgtDAOException {
        return false;
    }

    @Override
    public void addStream(EventStream stream) throws APIMgtDAOException {
        try (Connection connection = DAOUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(STREAM_INSERT)){
            try {
                connection.setAutoCommit(false);
                addStreamRelatedInformation(connection, statement, stream);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new APIMgtDAOException("adding Stream" + stream.getProvider() + "-" + stream.getName()  + "-"
                        + stream.getVersion());
            } finally {
                connection.setAutoCommit(DAOUtil.isAutoCommit());
            }
        } catch (SQLException e) {
            throw new APIMgtDAOException(DAOUtil.DAO_ERROR_PREFIX + "adding Stream" + stream.getProvider() + " - " +
            stream.getName() + " - " + stream.getVersion(), e);
        }
    }

    @Override
    public EventStream getEventStream(String streamID) throws APIMgtDAOException {
        final String query = STREAM_SELECT + " WHERE UUID = ? ";
        try (Connection connection = DAOUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, streamID);

            EventStream stream = constructStreamFromResultSet(connection, statement);
            if (stream == null) {
                throw new APIMgtDAOException("Stream with ID " + streamID + " does not exist", ExceptionCodes.STREAM_NOT_FOUND);
            }
            return stream;
        } catch (SQLException | IOException e){
            throw new APIMgtDAOException(DAOUtil.DAO_ERROR_PREFIX + "getting Stream: " + streamID, e);
        }

    }

    @Override
    public List<EventStream> getStreams(String user) throws APIMgtDAOException {
        final String query = STREAM_SUMMARY_SELECT + " WHERE PROVIDER = ? ";
        try (Connection connection = DAOUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            int index = 0;
            statement.setString(++index, user);
            return constructStreamSummaryList(connection, statement);
        } catch (SQLException e){
            throw new APIMgtDAOException(DAOUtil.DAO_ERROR_PREFIX + "getting Streams", e);
        }
    }

    /**
     * Method for adding Stream related information
     *
     * @param connection DB Connection
     * @param statement  PreparedStatement
     * @param stream     Stream object
     * @throws SQLException if error occurs while accessing data layer
     * */
    private void addStreamRelatedInformation(Connection connection, PreparedStatement statement, final EventStream stream)
            throws SQLException, APIMgtDAOException{

        String streamPrimaryKey = stream.getId();
        Gson gson = new Gson();
        statement.setString(1, stream.getId());
        statement.setString(2, stream.getProvider());
        statement.setString(3, stream.getName());
        statement.setString(4, stream.getVersion());
        statement.setString(5, stream.getDescription());
        statement.setString(6, String.valueOf(stream.getVisibility()));
        statement.setString(7, stream.getLifeCycleStatus());
        statement.setString(8, String.valueOf(stream.getEndpoint()));
        statement.setString(9, String.valueOf(stream.getStreamType()));
        statement.setString(10, gson.toJson(stream.getStreamAuthorization()));
        statement.setBoolean(11, stream.isProducable());
        statement.setBoolean(12, stream.isCanProducerAccessDirectly());
        statement.setBoolean(13, stream.isCanProducerAccessViaGateway());
        statement.setString(14, gson.toJson(stream.getProducerAuthorization()));
        statement.setString(15, gson.toJson(stream.getProducerTransport()));
        statement.setString(16, gson.toJson(stream.getProducerMessageType()));
        statement.setBoolean(17, stream.isConsumable());
        statement.setBoolean(18, stream.isCanConsumerAccessDirectly());
        statement.setBoolean(19, stream.isCanConsumerAccessViaGateway());
        statement.setString(20, gson.toJson(stream.getConsumerAuthorization()));
        statement.setString(21, gson.toJson(stream.getConsumerTransport()));
        statement.setString(22, gson.toJson(stream.getConsumerDisplay()));

        boolean rs = statement.execute();

    }


    private EventStream constructStreamFromResultSet(Connection connection, PreparedStatement statement) throws SQLException,
            IOException, APIMgtDAOException {
        Gson gson = new Gson();
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()){

                List<EventStream.Authorization> streamAuthorization = Arrays.asList(gson.fromJson(rs.getString("STREAM_AUTHORIZATION"),
                        EventStream.Authorization[].class));
                List<EventStream.Authorization> producerAuthorization = Arrays.asList(gson.fromJson(rs.getString("PRODUCER_AUTHORIZATION"),
                        EventStream.Authorization[].class));
                List<EventStream.Authorization> consumerAuthorization = Arrays.asList(gson.fromJson(rs.getString("CONSUMER_AUTHORIZATION"),
                        EventStream.Authorization[].class));
                List<EventStream.Transport> producerTransport = Arrays.asList(gson.fromJson(rs.getString("PRODUCER_TRANSPORT"),
                        EventStream.Transport[].class));
                List<EventStream.Transport> consumerTransport = Arrays.asList(gson.fromJson(rs.getString("CONSUMER_TRANSPORT"),
                        EventStream.Transport[].class));
                List<EventStream.MessageType> producerMessage = Arrays.asList(gson.fromJson(rs.getString("PRODUCER_MESSAGE_TYPE"),
                        EventStream.MessageType[].class));
                List<EventStream.Display> consumerDisplay = Arrays.asList(gson.fromJson(rs.getString("CONSUMER_DISPLAY"),
                        EventStream.Display[].class));



                return new EventStream.StreamBuilder(rs.getString("UUID"), rs.getString("NAME"), rs.getString("PROVIDER"),
                        rs.getString("VERSION")).
                        description(rs.getString("DESCRIPTION")).
                        lifeCycleStatus(rs.getString("LIFECYCLE_STATUS")).
                        streamType(Collections.singleton(rs.getString("STREAM_TYPE"))).
                        streamAuthorization(streamAuthorization).
                        visibility(EventStream.Visibility.valueOf(rs.getString("VISIBILITY"))).
                        isProducable(rs.getBoolean("IS_PRODUCABLE")).
                        canProducerAccessDirectly(rs.getBoolean("CAN_PRODUCER_ACCESS_DIRECTLY")).
                        canProducerAccessViaGateway(rs.getBoolean("CAN_PRODUCER_ACCESS_VIA_GATEWAY")).
                        producerAuthorization(producerAuthorization).
                        producerTransport(producerTransport).
                        producerMessageType(producerMessage).
                        isConsumable(rs.getBoolean("IS_CONSUMABLE")).
                        canConsumerAccessDirectly(rs.getBoolean("CAN_CONSUMER_ACCESS_DIRECTLY")).
                        canConsumerAccessViaGateway(rs.getBoolean("CAN_CONSUMER_ACCESS_VIA_GATEWAY")).
                        consumerAuthorization(consumerAuthorization).
                        consumerTransport(consumerTransport).
                        consumerDisplay(consumerDisplay).build();
            }
        }
        return null;
    }


    private List<EventStream> constructStreamSummaryList(Connection connection, PreparedStatement statement) throws SQLException {
        List<EventStream> streamList = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                EventStream streamSummary = new EventStream.StreamBuilder(rs.getString("UUID"), rs.getString("NAME"),
                        rs.getString("PROVIDER"), rs.getString("VERSION")).
                        lifeCycleStatus(rs.getString("LIFECYCLE_STATUS")).build();

                streamList.add(streamSummary);
            }

        }
        return streamList;
    }

}
