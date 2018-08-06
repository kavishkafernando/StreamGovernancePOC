package org.wso2.carbon.apimgt.rest.api.publisher.factories;

import org.wso2.carbon.apimgt.rest.api.publisher.StreamApiService;
import org.wso2.carbon.apimgt.rest.api.publisher.impl.StreamApiServiceImpl;

public class StreamApiServiceFactory {

    private static final  StreamApiService service = new StreamApiServiceImpl();

    public static StreamApiService getStreamApi() {
        return service;
    }
}
