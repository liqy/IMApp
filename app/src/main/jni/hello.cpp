#include <jni.h>
/* 动态注册 Java中的对应类名 注意斜杠的方向*/
const char *ClassName = "com/liqy/imapp/HelloJni";
extern "C" {

JNIEXPORT jstring JNICALL
fromJniString(JNIEnv *env, jobject instance) {
    // TODO
    return env->NewStringUTF("Hello from JNI ! 动态 Compiled with ABI ");
}

//region 动态注册方法
/**
 * 声明需要动态注册的方法
 * typedef struct {
 * const char* name;//Java方法的名字
 * const char* signature;//Java方法的签名信息
 * void*       fnPtr;//JNI中对应的方法指针
} JNINativeMethod;
 */
static JNINativeMethod gMethods[] = {
        {"fromJniString", "()Ljava/lang/String;", (void *) fromJniString}//对应java中的public native String stringFromJNI();
};

/****
 * 注册方法
 */
static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

/****
 * 注册类
 */
static int registerNatives(JNIEnv *env) {
    return registerNativeMethods(env, ClassName, gMethods, sizeof(gMethods) / sizeof(gMethods[0]));
}
// endregion

//region  初始化默认函数
/****
 * 默认函数
 * 在调用 System.loadLibrary 时会调用，不需要手动调用
 */
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = -1;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    if (!registerNatives(env)) {//注册
        return -1;
    }
    //成功
    result = JNI_VERSION_1_4;
    return result;
}

}