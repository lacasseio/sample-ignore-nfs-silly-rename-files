package com.example;

import org.apache.tools.ant.DirectoryScanner;
import org.gradle.api.Plugin;
import org.gradle.api.initialization.Settings;

public class GlobalNfsIgnorePlugin implements Plugin<Settings> {
    @Override
    public void apply(Settings settings) {
        DirectoryScanner.addDefaultExclude("**/.nfs*");
    }
}
