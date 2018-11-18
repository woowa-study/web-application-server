package webserver;

import domain.HttpRequestDto;

import java.io.DataOutputStream;

public interface PathHandleStrategy {
    void response(HttpRequestDto httpRequestDto, DataOutputStream dos);
}
