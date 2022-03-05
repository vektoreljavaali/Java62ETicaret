package com.vektorel.utility;

import androidx.annotation.Nullable;

public class StaticValues {

    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME_NOTE ="tblnote";
    /**
     * id,
     * username,
     * title,
     * content,
     * publishAt,
     * status,
     * priority
     */
    public static final String TBL_ID = "id";
    public static final String TBL_USERNAME = "username";
    public static final String TBL_TITLE = "title";
    public static final String TBL_CONTENT = "content";
    public static final String TBL_PUBLISHAT = "publishat";
    public static final String TBL_STATUS = "status";
    public static final String TBL_PRIORITY = "priority";

    public static final String AWSSERVER= "http://ec2-18-223-22-64.us-east-2.compute.amazonaws.com";

    /**
     * https://newsapi.org/v2/everything?
     * q=ankara&
     * language=tr&
     * from=2022-02-26&to=2022-02-27&
     * sortBy=popularity&
     * category=health
     * apiKey=86fec14ae3a54bd8a8cbb0f9f3ab517a
     * @return
     */
    public static String getNewsUrl(@Nullable String ArananKelime,@Nullable String dil,
                                    @Nullable String startDate,@Nullable String endDate,
                                    @Nullable String sortBy,@Nullable String category){
        String query=null;
        query= ArananKelime!=null ? "q="+ArananKelime: null;
        query= query!=null
                ? query+ dil !=null ? "&language="+dil : ""
                : dil !=null ? "language="+dil : null;
        query= query!=null
                ? query+ startDate !=null ? "&from="+startDate+"&to="+endDate : ""
                : dil !=null ? "&from="+startDate+"&to="+endDate : null;
        query= query!=null
                ? query+ sortBy !=null ? "&sortBy="+sortBy : ""
                : dil !=null ? "sortBy="+sortBy : null;
        query= query!=null
                ? query+ category !=null ? "&category="+category : ""
                : dil !=null ? "category="+category : null;
        return  "https://newsapi.org/v2/everything?"+query+"&apiKey=86fec14ae3a54bd8a8cbb0f9f3ab517a";
    }

}
