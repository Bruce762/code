package org.example;

public interface GitHubService {
    int getLines(String gitHubRepo);
    int getWMC(String gitHubRepo);
}