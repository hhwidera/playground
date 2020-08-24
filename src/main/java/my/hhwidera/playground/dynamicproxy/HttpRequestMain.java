package my.hhwidera.playground.dynamicproxy;

class HttpRequestMain {

    public static void main(String[] args) {
        GoogleClient googleClient = TimingProxy.bind(HttpRequestProxy.create(GoogleClient.class));
        System.out.println(googleClient.google());
    }

    interface GoogleClient {
        @Request(method = "POST", uri = "https://www.google.de")
        String google();
    }

}
