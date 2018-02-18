# Amazfit Springboard Plugin Example

This project is an example for how to create a custom page for the default home screen (called "Springboard") on the Amazfit Pace

## Usage
You don't need to import this project and edit it from there. There's only a couple of important files and pieces of code:

### SpringboardPluginLib.jar (app/libs)
Disassembled code from the HmAlarmClock app, with all but the plugin code removed. [Download it](https://github.com/KieronQuinn/AmazfitSpringboardPluginExample/raw/master/app/libs/SpringboardPluginLib.jar), copy it to the libs folder of your project, and then include it like so:

![File > Project Structure > Dependencies](https://i.imgur.com/xIrVhJp.png)

### SpringboardPage.java (app/src/main/java/com/kieronquinn/app/springboardexample)
Example code for implementing a page. Copy this to your project, and edit it as you like. It's commented, so each method is labelled with what it does

### AndroidMainfest.xml (app/src/main)

**Do not simply copy this to your project**

Only the following section is required:

`<meta-data android:name="com.huami.watch.launcher.springboard.PASSAGER_TARGET" android:resource="@array/spring_depend" />`

Place this inside your application tags, as shown in the example in this project

### arrays.xml (app/src/main/res/values)

Copy this file to your project (or create the file and copy the contents), then edit the contents of the \<item> \</item> tags to point to your SpringboardPage class.

If you rename your SpringboardPage class, you **must** change it here also. Make sure the package name **and** component is correct here, or the page will not work

### widget_blank.xml (app/src/main/res/layout)

You don't need to copy this file, you can create your own layout file and edit the SpringboardPage class accordingly

## Installation
Run your app as normal. If you created a project without an activity, you may need to use Build > Build APK and install it via adb

Now, the first time you install the app it will not immediately appear in the launcher. Either reboot the watch, or run `adb shell am force-stop com.huami.watch.launcher` to restart the launcher

After this it should appear as the last page. If it has, well done! If not, check you followed every step correctly (particularly the arrays.xml and AndroidManifest.xml ones). Still not working? [Post on the XDA thread](https://forum.xda-developers.com/smartwatch/amazfit/dev-create-custom-home-screen-pages-pace-t3751731)

## Moving the page
There's no built in way to move or disable the page on the watch or the Amazfit app. Luckily, [I've already got a solution for that](https://github.com/KieronQuinn/AmazfitSpringboardSettings)
