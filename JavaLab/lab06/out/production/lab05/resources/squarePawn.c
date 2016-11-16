#include <jni.h>
#include <stdio.h>
#include "game_Pawn.h"

JNIEXPORT jobjectArray JNICALL Java_game_Pawn_placePawn
  (JNIEnv *env, jobject  thisObj, jint size, jobjectArray grid,jint x,jint y){
      for (int i = 0; i < size; i++) {
         jintArray temp= (jintArray) (*env)->GetObjectArrayElement(env,grid, i);
         jint* number = (*env)->GetIntArrayElements(env,temp,0);
         for (int j = 0; j < size; j++) {
           if(i+3>x&&i-3<x&&j+3>y&&j-3<y){
             number[j]=1;
           }
         }
         (*env)->ReleaseIntArrayElements(env, temp, number,0);
      }
       return grid;
  };

  JNIEXPORT jstring JNICALL Java_game_Pawn_describe
    (JNIEnv *env, jobject  thisObj){
      return (*env)->NewStringUTF(env,"tworze kwadrat 3x3");
    };
