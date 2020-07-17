package dependencies

object Versions {

    const val compileSdkVersion = 29
    const val minSdkVersion = 16
    const val targetSdkVersion = 29

    private const val majorVersion = 0
    private const val minorVersion = 0
    private const val patchVersion = 6

    const val versionCode = (100 * majorVersion) + (10 * minorVersion) + patchVersion
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
}