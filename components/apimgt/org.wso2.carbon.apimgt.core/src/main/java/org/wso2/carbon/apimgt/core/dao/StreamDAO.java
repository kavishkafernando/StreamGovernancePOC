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
package org.wso2.carbon.apimgt.core.dao;

import org.wso2.carbon.apimgt.core.exception.APIMgtDAOException;
import org.wso2.carbon.apimgt.core.streams.EventStream;
/**
 * Provides access to Stream data layer.
 */
public interface StreamDAO {

    /**
     * Check if an Stream exists for a given streamID
     * @param streamID The UUID that uniquely identifies an Stream
     * @return true if Stream exists else false
     * @throws APIMgtDAOException if error occurs while accessing data layer
     */
    boolean isStreamExists(String streamID) throws APIMgtDAOException;

    /**
     * Retrieve a given instance of an Stream
     *
     * @param streamID The UUID that uniquely identifies an Stream
     * @return valid {@link EventStream} object or throws APIMgtDAOException
     * @throws APIMgtDAOException if error occurs while accessing data layer
     *
     */
    EventStream getStream(String streamID) throws APIMgtDAOException;

    /**
     * Checks if a given API which is uniquely identified by the Provider, API Name and Version combination already
     * exists
     *
     * @param streamName Name of Stream
     * @param providerName Provider of the Stream.
     * @return true if providerName, apiName, version combination already exists else false
     * @throws APIMgtDAOException if error occurs while accessing data layer
     */
    boolean isStreamNameExists(String streamName, String providerName) throws APIMgtDAOException;

    /**
     * Add a new instance of an API
     *
     * @param stream The {@link EventStream} object to be added
     * @throws APIMgtDAOException if error occurs while accessing data layer
     *
     */
    void addStream(EventStream stream) throws APIMgtDAOException;
}
