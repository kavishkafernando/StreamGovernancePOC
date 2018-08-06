/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.apimgt.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.dao.StreamDAO;
import org.wso2.carbon.apimgt.core.exception.APIMgtDAOException;
import org.wso2.carbon.apimgt.core.streams.EventStream;
import org.wso2.carbon.apimgt.core.streams.FileStream;
import org.wso2.carbon.apimgt.core.util.APIFileUtils;


/**
 * File based implementation of the StreamDAO interface.
 */
public class StreamFileDAO implements StreamDAO {

    private static final Logger log = LoggerFactory.getLogger(ApiFileDAOImpl.class);
    private String storagePath;

    public StreamFileDAO(String storagePath){
        this.storagePath = storagePath;
    }

    @Override
    public boolean isStreamExists(String streamID) throws APIMgtDAOException {
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
        //Save Stream definition
        FileStream fileStream = new FileStream(stream);
        String streamExportDirectory = APIFileUtils.getStreamBaseDirectory(storagePath, fileStream);
        APIFileUtils.createDirectory(streamExportDirectory);
        APIFileUtils.exportStreamDefinitionToFileSystem(fileStream, streamExportDirectory);
    }
}
