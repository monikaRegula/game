package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

/**
 * Class Integrator
 * @author Monika Reguła Katarzyna Jurkowska
 * @version.
 */
public class Integrator {

    /**
     *Represents mass
      */
    private double mV;
    /**
     * Represents velocity
     */
    private double vV;
    /**
     * Represents heigtht
     */
    private double hV;
    /**
     * Represents step of integration
     */
    private double dt;
    /**
     * Represents counter
     */
    private int n=0;
    /**
     * Represents arraylist of masses
     */
    ArrayList<Double> mValues = new ArrayList<>();
    /**
     * Represents arraylist of heights
     */
    ArrayList<Double> hValues = new ArrayList<>();
    /**
     * Represents arraylist of velocities
     */
    ArrayList<Double> vValues = new ArrayList<>();
    /**
     * Represents arraylist of fuel Usage
     */
    ArrayList<Double> uValues = new ArrayList<>();

    /**
     * Method gets counter
     * @return n
     */
    public int getN() { return n; }

    /**
     * Method sets counter
     * @param n n
     */
    public void setN(int n) { this.n = n; }

    /**
     * Method gets mass
     * @return mass
     */
    public double getmV() { return mV; }

    /**
     * Method gets velocity
     * @return velocity
     */
    public double getvV() { return vV; }

    /**
     * Method gets heigth
     * @return heigth
     */
    public double gethV() { return hV; }

    /**
     * Method sets mass
     * @param mV mass
     */
    public void setmV(double mV) { this.mV = mV; }

    /**
     * Method sets velocity
     * @param vV velocity
     */
    public void setvV(double vV) { this.vV = vV; }

    /**
     * Method sets height
     * @param hV height
     */
    public void sethV(double hV) { this.hV = hV; }

    /**
     * Makes an object with parameter:
     * @param dt dt (step of integration)
     */
    public Integrator(double dt) {
        this.dt = dt;
    }

    /**
     * Method as an argument has objects: Rocket and SlideUpdate. It integrates. It updates calculated values.
     *Initial value of fuel usage is set as zero. For first integration there is another method. It initializes values of the rocket.
     * @param rocket rocket

     */
    public void integrate(Rocket rocket){
        uValues.add(0, (double) 0);

        if (getN()==0){
            initializeRocket(rocket);
        }
        else{
            double m,v,h;
            double g = 1.63;
            double u = uValues.get(getN()+1);

            m = getmV();
            v = getvV();
            h = gethV();
            System.out.println("m "+m+" v"+v+" h"+h+ " u"+u);

            if(m<=1005) { //paliwo się skończyło rakieta opada
                u = 0;
            }

            update(m,v,h,u);

            double vNew = -g - ((rocket.getK()*u)/m);
            double hNew = v;
            double mNew = u;

            if(m<=1005) //warunek o masie, gdy skończy się paliwo
                mNew=0;

            System.out.println("m "+mNew+" v"+vNew+" h"+hNew);


            v=v+vNew;
            h=h+hNew;
            m=m+mNew;

            update(m,v,h,u);

            System.out.println("m "+m+" v"+v+" h"+h);
            setmV(m);
            sethV(h);
            setvV(v);

            //metoda wywołująca poruszanie się Buzza
            Controller.getController().rocketViewLanding(h);

            if(h<0){
            update(m,v,0,u);
            }
        }
    }

    /**
     * Method sets initial values for object rocket:
     * mass = 2730.14 kg
     * velocity = - 150 m/s
     * height = 50 000 m
     * @param rocket rocket
     */
    //ustawia wartośći początkowe rakiety
    public void initializeRocket(Rocket rocket){
        setmV(rocket.getMass());
        setvV(rocket.getVelocity());
        sethV(rocket.getHeight());
        uValues.add(0,0.0);
        System.out.println("Ustawiam wartości początkowe rakiety !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("mV "+mV + " hV" +hV + " vV"+vV);
    }


    /**
     * Method clears arraylists
     */
    public void clearData(){
        mValues.clear();
        hValues.clear();
        vValues.clear();
    }


    /**
     * Method updates values and adds them to the arraylists.
     * @param m mass
     * @param v velocity
     * @param h height
     * @param u fuel usage
     */
    public void update(double m, double v, double h, double u) {
        mValues.add(m);
        vValues.add(v);
        hValues.add(h);
    }

    /**
     * Method shows values in console
     */
    public void show(){
        System.out.println("m: "+getmV() +" v: "+getvV()+ " h: "+gethV());
        System.out.println();
    }


}
