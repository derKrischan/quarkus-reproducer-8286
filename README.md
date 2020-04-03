# quarkus-reproducer-8286

Trying to reproduce https://github.com/quarkusio/quarkus/issues/8286

Fails with 

`````
[ERROR] givenCallToProfilesForName_returnsProfileData  Time elapsed: 0.828 s  <<< ERROR!
org.mockito.exceptions.base.MockitoException:

ClassCastException occurred while creating the mockito mock :
  class to mock : 'com.example.quarkus.profileservice.ProfileService', loaded by classloader : 'jdk.internal.loader.ClassLoaders$AppClassLoader@c0bf1bd6'
  created class : 'org.mockito.codegen.ProfileService$MockitoMock$2125970254', loaded by classloader : 'net.bytebuddy.dynamic.loading.MultipleParentClassLoader@f018f3a2'
  proxy instance class : 'org.mockito.codegen.ProfileService$MockitoMock$2125970254', loaded by classloader : 'net.bytebuddy.dynamic.loading.MultipleParentClassLoader@f018f3a2'
  instance creation by : ObjenesisInstantiator

You might experience classloading issues, please ask the mockito mailing-list.

Caused by: java.lang.ClassCastException: Cannot cast class org.mockito.codegen.ProfileService$MockitoMock$2125970254 to class com.example.quarkus.profileservice.ProfileService
`````

When `@ExtendWith(MockitoExtension.class)`is removed in com.example.quarkus.profileservice.ProfilesControllerTest there is no ClassCastException but it fails because the ProfileService is not beeing mocked.

