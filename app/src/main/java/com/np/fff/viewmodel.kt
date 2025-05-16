package com.np.fff

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class MyViewModel : ViewModel(){

    var Igraigrac by mutableIntStateOf(1)
        private set

    var bodovi1 by mutableIntStateOf(0)
        private set

    var bodovi2 by mutableIntStateOf(0)
        private set

    var iskopano by mutableStateOf("")
         private set

    fun potezIgrac1(){

        if(Igraigrac==1){
            Igraigrac++;
        }
        var broj by mutableIntStateOf(0)
        broj= Random.nextInt(100)

          if(broj<60){
              iskopano="Zlato"

      }
        if(broj>60 && broj<90){
            iskopano="Kamen"

        }
        if(broj<100 && broj>90){
            iskopano="Slomljen Alat"

        }

        if(iskopano=="Zlato"){
            bodovi1++;
        }
        if(iskopano=="Slomljen Alat"){
            if(bodovi1!=0){
                bodovi1--;
            }
        }



    }

    fun potezIgrac2(){
    if(Igraigrac==2){
        Igraigrac--
    }



        var broj by mutableIntStateOf(0)
        broj= Random.nextInt(100)

        if(broj<60){
            iskopano="Zlato"

        }
        if(broj>60 && broj<90){
            iskopano="Kamen"

        }
        if(broj<100 && broj>90){
            iskopano="Slomljen Alat"

        }

        if(iskopano=="Zlato"){
            bodovi2++;
        }
        if(iskopano=="Slomljen Alat"){
            if(bodovi2!=0){
                bodovi2--;
            }

        }
    }






    fun reset(){
        bodovi2=0;
        bodovi1=0;
        Igraigrac=1;

    }


}