package me.gnevilkoko.threads.StreamThread.models.WebSocketRequests;

public class RequestDonationSubscription extends WebSocketRequestBase {
    private Parameters params;
    private int method;
    private int id;

    public RequestDonationSubscription(String channelToken, String channelName){
        params = new Parameters(channelName, channelToken);
        method = 1;
        id = 2;
    }

    public Parameters getParams() {
        return params;
    }

    public void setParams(Parameters params) {
        this.params = params;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public class Parameters {
        private String channel;
        private String token;

        public Parameters() {
        }

        public Parameters(String channel, String token) {
            this.channel = channel;
            this.token = token;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
