package luisa.almeida.secondrestaurantapi.responses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Response {

    private String status;
    private String sentOn;
    private String statusCode;
    private String transactionID;
    private String msg;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSentOn() {
        return sentOn;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void generateCurrentTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        this.sentOn = dateTime.format(dateFormatter);
    }

    public void generateResponse(String status, String statusCode, String msg) {
        this.status = status;
        this.statusCode = statusCode;
        this.msg = msg;
        generateCurrentTime();
        this.transactionID = UUID.randomUUID().toString();
    }

}
