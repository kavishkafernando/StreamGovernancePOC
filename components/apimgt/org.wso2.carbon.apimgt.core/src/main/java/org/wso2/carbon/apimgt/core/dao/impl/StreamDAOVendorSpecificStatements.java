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

import org.wso2.carbon.apimgt.core.dao.SearchType;


import java.util.Locale;
/**
 *  Interface for getting SQL Statement strings. Implementation of the interface could return different values based
 *  on DB vendor type being used
 */
public interface StreamDAOVendorSpecificStatements {

    /**
     * Maps the search attribute specified with the relevant DB table/column that needs to be queried.
     * This default implementation is suitable for most DB implementations and can be overridden to support specific
     * requirements of a DB implementation.
     *
     * @param attributeKey Key fo the attribute that is being searched
     * @return DBTableMetaData which contains the relevant DB table/column combination
     */
    default DBTableMetaData mapSearchAttributesToDB(SearchType attributeKey) {
        DBTableMetaData metaData = new DBTableMetaData();

            //if the search is related to any other attribute, need to check that attribute
            //in AM_API table
            metaData.setTableName(SQLConstants.AM_STREAM_TABLE_NAME);
            metaData.setColumnName(attributeKey.toString().toUpperCase(Locale.ENGLISH));
        return metaData;
    }
}
