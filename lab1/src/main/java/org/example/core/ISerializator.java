package org.example.core;

import java.io.IOException;

public interface ISerializator {
    void serialize(Object obj) throws IOException;

    Object deserialize() throws IOException, ClassNotFoundException;
}
