# Working with Gradle on NFS drives

Because of "silly rename" (https://nfs.sourceforge.net/#faq_d2), there is a race condition where Gradle may list `.nfsXXXX` files for caching, but they get deleted before Gradle reads them. 
This will result in crash like `Could not read symlink <...>/.nfsXXXXXX: could not lstate file (errno2: No such file or directory)`.
The whole infrastructure for working with file is shared across Gradle, hence with the directory listing for output directories (build cache) and virtual file system.
Gradle inherit a list of default exclude patterns from ANT which are configurable via `DirectoryScanner#addDefaultExclude` (see https://docs.gradle.org/current/userguide/working_with_files.html#sec:file_trees).
This sample demonstrate the issue by mocking a compile task that "introduce the scenario of listable but non-readable `.nfs` files.
The plugin will exclude `.nfs` files from ever being considered by any `FileTree` (directory listing).
Note the plugin needs to be applied by all build working running on NFS drives.

Resources:
- https://serverfault.com/questions/201294/nfsxxxx-files-appearing-what-are-those
- https://github.com/gradle/gradle/issues/1348
- https://github.com/gradle/gradle/issues/11176
- https://github.com/gradle/gradle/issues/13427