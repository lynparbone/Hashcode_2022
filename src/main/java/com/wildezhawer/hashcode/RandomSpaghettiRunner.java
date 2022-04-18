package com.wildezhawer.hashcode;

import com.wildezhawer.hashcode.service.DataStore;
import com.wildezhawer.hashcode.service.FileReaderService;
import com.wildezhawer.hashcode.service.FileWriterService;

import java.util.ArrayList;

import static com.wildezhawer.hashcode.config.SimulationConfiguration.FILES_TO_READ;
import static com.wildezhawer.hashcode.service.ProjectSorter.sortProjectsByDurationAscending;

public class RandomSpaghettiRunner {

    FileReaderService fileReader = new FileReaderService();
    FileWriterService fileWriter = new FileWriterService();

    public void start() throws Exception {
        for (String filename : FILES_TO_READ) {
            // Preparation for simulation
            read(filename);
            DataStore.projects = sortProjectsByDurationAscending(DataStore.projects);

            // TODO: Run simulation, i.e. plan the projects

            // Write the output files in the correct format
            write(filename);
            resetDatastore();
        }
    }

    private void read(String filename) throws Exception {
        fileReader.read("input/" + filename);
    }

    private void write(String filename) throws Exception {
        fileWriter.writeFile("output/" + filename);
    }

    private void resetDatastore() {
        DataStore.projects = new ArrayList<>();
        DataStore.contributors = new ArrayList<>();
        DataStore.plannedProjects = new ArrayList<>();
    }

}
