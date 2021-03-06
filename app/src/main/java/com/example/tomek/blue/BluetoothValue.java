package com.example.tomek.blue;

/**
 * Created by Tomek on 2015-06-08.
 */
public class BluetoothValue {
    public enum valuesTypes{  ACCELERATION(0),
        INVALID_VALUE(-1),
        ANGLE(1),
        BATTERY_VOLTAGE(2),
        BATTERY_CURRENT(3),
        SOLAR_VOLTAGE(4),
        ENGINE_POWER(5);

        private int value;

        valuesTypes(int value){

            this.value = value;
        }

        public int getValue(){
            return this.value;
        };

        static public valuesTypes getAppropriateValue(int number){
            valuesTypes temp;

            switch (number){
                case 0:
                    temp = valuesTypes.ACCELERATION;
                    break;
                case 1:
                    temp = valuesTypes.ANGLE;
                    break;
                case 2:
                    temp = valuesTypes.BATTERY_VOLTAGE;
                    break;
                case 3:
                    temp = valuesTypes.BATTERY_CURRENT;
                    break;
                case 4:
                    temp = valuesTypes.SOLAR_VOLTAGE;
                    break;
                case 5:
                    temp = valuesTypes.ENGINE_POWER;
                    break;
                default:
                    temp = valuesTypes.INVALID_VALUE;
            }

            return temp;
        }
    };

    protected static String TAG = "BluetoothConnector";
    protected boolean valueChanged = false;
    private static BluetoothValue __value = null;

    public static BluetoothValue getInstance(){
        return __value == null ? new BluetoothValue() : __value;
    }

    public void setValue(String s){}
    public void printValue(){}
    public static String getTypeName(){return "";}
}
