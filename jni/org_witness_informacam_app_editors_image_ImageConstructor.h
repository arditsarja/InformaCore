/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_witness_informa_utils_ImageConstructor */

#ifndef _Included_org_witness_informacam_app_editors_image_ImageConstructor
#define _Included_org_witness_informacam_app_editors_image_ImageConstructor
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_witness_informacam_app_editors_image_ImageConstructor
 * Method:    constructImage
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)I
 */
JNIEXPORT jint JNICALL Java_org_witness_informacam_app_editors_image_ImageConstructor_constructImage
  (JNIEnv *, jobject, jstring, jstring, jstring, jint);

/*
 * Class:     org_witness_informacam_app_editors_image_ImageConstructor
 * Method:    redactRegion
 * Signature: (Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_witness_informacam_app_editors_image_ImageConstructor_redactRegion
  (JNIEnv *, jobject, jstring, jstring, jint, jint, jint, jint, jstring);

#ifdef __cplusplus
}
#endif
#endif