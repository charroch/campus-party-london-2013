campus-party-london-2013
========================

Welcome to the 2013 Android workshop at CAMPUS PARTY 2013 LONDON.

What will we do?
----------------

We will make an Android application that relies on the native Account management to fetch a token. This token will then be used to fetch ones' activity stream from Google+.


What will you learn?
--------------------

Programming on android, Gradle, testing and some stuff in between.


What do I need?
---------------

We need some software to get this to work. Google is you friend here. Ensure you have the following installed:

- [Java](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html)
- [Git](http://git-scm.com/)
- [Android](http://developer.android.com/sdk/index.html)
- [Android Studio](http://developer.android.com/sdk/installing/studio.html)
- [Gradle](http://www.gradle.org/downloads)
- [A github account](https://github.com/novoda/campus-party-london-2013)
- [A google + account](http://google.com/+)
- A google+ API key if you wish to use your own certificate

Test your setup
---------------

```bash
javac -version # should print 1.6
git clone https://github.com/novoda/campus-party-london-2013
cd campus-party-london-2013
git checkout 1
cd campus-party-london-2013
gradle testSetup
android &
```
