package com.example.a42252248.evaluacionfebrero;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.RotateTo;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.Random;

/**
 * Created by 42252248 on 22/2/2018.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize DeviceDisplay;
    Sprite Auto1;
    Sprite Auto2;
    Sprite Auto3;
    Sprite Auto4;
    float PosIx1, PosIy1;
    boolean EnterMove;


        public clsJuego(CCGLSurfaceView VistaDelJuego) {
            _VistaDelJuego = VistaDelJuego;
        }

        public void ComenzarJuego() {

            Director.sharedDirector().attachInView(_VistaDelJuego);

            DeviceDisplay = Director.sharedDirector().displaySize();

            Director.sharedDirector().runWithScene(EscenaDelJuego());

        }

        private Scene EscenaDelJuego() {
            Scene EscenaADevolver;
            EscenaADevolver = Scene.node();

            CapaDeFondo MiCapaFondo;
            MiCapaFondo = new CapaDeFondo();

            CapaDelFrente MiCapaFrente;
            MiCapaFrente = new CapaDelFrente();

            EscenaADevolver.addChild(MiCapaFondo, -10);
            EscenaADevolver.addChild(MiCapaFrente, -10);



            return EscenaADevolver;


        }

        class CapaDeFondo extends Layer {

        }

        class CapaDelFrente extends Layer {



            public CapaDelFrente(){
                PonerSpritesPosInicial();
                this.setIsTouchEnabled(true);
            }
            @Override
            public boolean ccTouchesBegan(MotionEvent event){

                Random rand = new Random();
                int aleatorio = rand.nextInt(4)+1;

                Log.d("random", "que vale " + aleatorio);

                if (aleatorio == 1){
                    Auto1.runAction(RotateBy.action(1f, 360));
                    Log.d("rotacion", "rota el rayo");
                }else if (aleatorio == 2){
                    Auto2.runAction(RotateBy.action(1f, 360));
                    Log.d("rotacion", "rota mate");
                }else if (aleatorio == 3){
                    Auto3.runAction(RotateBy.action(1f, 360));
                    Log.d("rotacion", "rota sally");
                }else if (aleatorio == 4){
                    Auto4.runAction(RotateBy.action(1f, 360));
                    Log.d("rotacion", "rota el sheriff");
                }

                Random rand1 = new Random();
                int aleatorio1 = rand.nextInt(4)+1;

                while (aleatorio == aleatorio1){
                    aleatorio1 = rand.nextInt(4)+1;
                }

                Log.d("random", "que vale " + aleatorio1);
                if(aleatorio != aleatorio1) {

                    if (aleatorio1 == 1) {
                        Auto1.runAction(RotateBy.action(1f, -360));
                        Log.d("rotacion", "rota pal otro lado el rayo");
                    } else if (aleatorio1 == 2) {
                        Auto2.runAction(RotateBy.action(1f, -360));
                        Log.d("rotacion", "rota pal otro lado mate");
                    } else if (aleatorio1 == 3) {
                        Auto3.runAction(RotateBy.action(1f, -360));
                        Log.d("rotacion", "rota pal otro lado sally");
                    } else if (aleatorio1 == 4) {
                        Auto4.runAction(RotateBy.action(1f, -360));
                        Log.d("rotacion", "rota pal otro lado el sheriff");
                    }
                }

                return true;
            }
            @Override
            public boolean ccTouchesMoved(MotionEvent event){

                EnterMove = true;

                Auto1.runAction(MoveTo.action(1, PosIx1 + 30, PosIy1));


                return true;
            }
            @Override
            public boolean ccTouchesEnded(MotionEvent event){
                if (EnterMove && PosIx1 < DeviceDisplay.width - Auto1.getWidth()/2 + -50) {
                    PosIx1 += 30;
                    EnterMove = false;
                }
                return true;
            }

            private void PonerSpritesPosInicial(){


                PosIx1 = DeviceDisplay.width /2;
                PosIy1 = DeviceDisplay.height / 2 - 250;
                Auto1 = Sprite.sprite("rayo.png");
                Auto1.setPosition(PosIx1, PosIy1);
                super.addChild(Auto1);

                Auto2 = Sprite.sprite("mate.png");
                float PosIx2, PosIy2;
                PosIx2 = DeviceDisplay.width /2 + 250;
                PosIy2 = DeviceDisplay.height / 2;
                Auto2.setPosition(PosIx2, PosIy2);
                super.addChild(Auto2);

                Auto3 = Sprite.sprite("sally.png");
                float PosIx3, PosIy3;
                PosIx3 = DeviceDisplay.width /2 -250;
                PosIy3 = DeviceDisplay.height / 2;
                Auto3.setPosition(PosIx3, PosIy3);
                super.addChild(Auto3);

                Auto4 = Sprite.sprite("sheriff.png");
                float PosIx4, PosIy4;
                PosIx4 = DeviceDisplay.width /2;
                PosIy4 = DeviceDisplay.height / 2 +250;
                Auto4.setPosition(PosIx4, PosIy4);
                super.addChild(Auto4);

            }

        }

    }

