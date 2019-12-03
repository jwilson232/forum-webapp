package com.mastek.training.Forum.common;

public interface Constants {

    interface Common {

        String USERS = "/users";
        String THREADS = "/threads";
        String COMMENTS = "/comments";
        String CREATE = "/create";
        String DELETE = "/delete";
        String SORT = "/sort";
        String RANK = "/rank";
        String DESC = "/desc";
        String ASC = "/asc";
        String SEARCH = "/search";

    }

    interface  ViewNames {

        // For UI pages - for example "https://localhost:9090/views/pages/threads"

    }

    interface Header {

        // For postman header field

    }

    interface URIPaths {

        String USER_ID = "/{userID}";
        String THREAD_ID = "/{threadId}";

    }

    interface URIParams {

        String USER_ID = "userId";
        String THREAD_ID = "threadId";

    }

}
