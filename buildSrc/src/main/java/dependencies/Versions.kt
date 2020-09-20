package dependencies

object Versions {

    const val compileSdkVersion = 30
    const val minSdkVersion = 16
    const val targetSdkVersion = 30

    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0

    const val versionCode = (100 * majorVersion) + (10 * minorVersion) + patchVersion
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
}