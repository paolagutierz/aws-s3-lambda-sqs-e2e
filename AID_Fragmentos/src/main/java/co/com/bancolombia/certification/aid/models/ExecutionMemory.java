package co.com.bancolombia.certification.aid.models;

import java.util.Map;

public class ExecutionMemory {

    private static String apiEndpoint;
    private static String transactionId;
    private static String fragmentId;
    private static Map<String, String> headers;
    private static String bodyString;
    private static String response;
    private static String expectedResponse;
    private static String scenarioController;
    private static Integer timeElapsed;
    private static Integer timeExpected;

    private ExecutionMemory(){
        //Do nothing.
    }

    public static String getTransactionId() {
        return transactionId;
    }

    public static void setTransactionId(String transactionId) {
        ExecutionMemory.transactionId = transactionId;
    }

    public static String getFragmentId() {
        return fragmentId;
    }

    public static void setFragmentId(String fragmentId) {
        ExecutionMemory.fragmentId = fragmentId;
    }

    public static Map<String, String> getHeaders() {
        return headers;
    }

    public static void setHeaders(Map<String, String> headers) {
        ExecutionMemory.headers = headers;
    }

    public static String getBodyString() {
        return bodyString;
    }

    public static void setBodyString(String bodyString) {
        ExecutionMemory.bodyString = bodyString;
    }

    public static String getApiEndpoint() {
        return apiEndpoint;
    }

    public static void setApiEndpoint(String apiEndpoint) {
        ExecutionMemory.apiEndpoint = apiEndpoint;
    }

    public static String getResponse() {
        return response;
    }

    public static void setResponse(String response) {
        ExecutionMemory.response = response;
    }

    public static String getExpectedResponse() {
        return expectedResponse;
    }

    public static void setExpectedResponse(String expectedResponse) {
        ExecutionMemory.expectedResponse = expectedResponse;
    }

    public static String getScenarioController() {
        return scenarioController;
    }

    public static void setScenarioController(String scenarioController) {
        ExecutionMemory.scenarioController = scenarioController;
    }

    public static Integer getTimeExpected() {
        return timeExpected;
    }

    public static void setTimeExpected(Integer timeExpected) {
        ExecutionMemory.timeExpected = timeExpected;
    }

    public static Integer getTimeElapsed() {
        return timeElapsed;
    }

    public static void setTimeElapsed(Integer timeElapsed) {
        ExecutionMemory.timeElapsed = timeElapsed;
    }
}