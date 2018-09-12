# Don't warn me this class
-dontwarn com.google.android.gms.auth.GoogleAuthUtil

# They are used inside BottomNavigationViewHelper via reflection, proguard should not remove them
-keep class android.support.design.internal.BottomNavigationItemView{ *; }
-keep class android.support.design.internal.BottomNavigationMenuView{ *; }