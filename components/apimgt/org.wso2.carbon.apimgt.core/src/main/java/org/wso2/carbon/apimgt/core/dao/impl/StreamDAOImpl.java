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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.dao.StreamDAO;
import org.wso2.carbon.apimgt.core.exception.APIMgtDAOException;
import org.wso2.carbon.apimgt.core.streams.EventStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        statement.setString(1, stream.getId());
        statement.setString(2, stream.getProvider());
        statement.setString(3, stream.getName());
        statement.setString(4, stream.getVersion());
        statement.setString(5, stream.getDescription());
        statement.setString(6, String.valueOf(stream.getVisibility()));
        statement.setString(7, stream.getLifeCycleStatus());
        statement.setString(8, String.valueOf(stream.getEndpoint()));
        statement.setString(9, String.valueOf(stream.getStreamType()));
        statement.setString(10, String.valueOf(stream.getStreamAuthorization()));
        statement.setBoolean(11, stream.isProducable());
        statement.setBoolean(12, stream.isCanProducerAccessDirectly());
        statement.setBoolean(13, stream.isCanProducerAccessViaGateway());
        statement.setString(14, String.valueOf(stream.getProducerAuthorization()));
        statement.setString(15, String.valueOf(stream.getProducerTransport()));
        statement.setString(16, String.valueOf(stream.getProducerMessageType()));
        statement.setBoolean(17, stream.isConsumable());
        statement.setBoolean(18, stream.isCanConsumerAccessDirectly());
        statement.setBoolean(19, stream.isCanConsumerAccessViaGateway());
        statement.setString(20, String.valueOf(stream.getConsumerAuthorization()));
        statement.setString(21, String.valueOf(stream.getConsumerTransport()));
        statement.setString(22, String.valueOf(stream.getConsumerDisplay()));

        boolean rs = statement.execute();

    }



//    static void initStream() throws APIMgtDAOException {
//        try (Connection connection = DAOUtil.getConnection()) {
//            try {
//                if (!isStreamExist(connection)) {
//                    connection.setAutoCommit(false);
//                    addStream(connection);
//                    connection.commit();
//                }
//            } catch (SQLException e) {
//                connection.rollback();
//                throw new APIMgtDAOException(DAOUtil.DAO_ERROR_PREFIX + "adding API types", e);
//            } finally {
//                connection.setAutoCommit(DAOUtil.isAutoCommit());
//            }
//        } catch (SQLException e) {
//            throw new APIMgtDAOException(DAOUtil.DAO_ERROR_PREFIX + "adding API types", e);
//        }
//    }

}
