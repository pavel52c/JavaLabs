package org.example.core;

import org.example.dto.DataStorage;

import java.util.List;

public interface IReadSCVFile {
    List<DataStorage> readDataStorages(String filename);
}
