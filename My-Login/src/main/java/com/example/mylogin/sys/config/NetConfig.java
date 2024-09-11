package com.example.mylogin.sys.config;

public class NetConfig {
    public float confThreshold; // Confidence threshold
    public float nmsThreshold;  // Non-maximum suppression threshold
    public int inpWidth;
    public int inpHeight;
    public String classesFile;  // Class file name
    public String modelConfiguration; // Model configuration file
    public String modelWeights;  // Model weights
    public String netname;  // Model name

    public NetConfig(float confThreshold, float nmsThreshold, int inpWidth, int inpHeight, String classesFile, String modelConfiguration, String modelWeights, String netname) {
        this.confThreshold = confThreshold;
        this.nmsThreshold = nmsThreshold;
        this.inpWidth = inpWidth;
        this.inpHeight = inpHeight;
        this.classesFile = classesFile;
        this.modelConfiguration = modelConfiguration;
        this.modelWeights = modelWeights;
        this.netname = netname;
    }
}
