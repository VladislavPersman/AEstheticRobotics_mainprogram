package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOPv2", group="teleop")
//@Disabled
public class TeleOP_V2 extends LinearOpMode {


    Pushbot_Teleop robot = new Pushbot_Teleop();   // Импортируем pushbot

    @Override
    public void runOpMode() {
        double x1 = 0;//left/right;
        double y1 = 0;//front/back;

        double sixtyinrads = -Math.PI / 3;
        double thirtyfiveinrads = -Math.PI / 6;
        double cos60 = Math.cos(sixtyinrads);
        double sin30 = Math.sin(thirtyfiveinrads);
        double x2 = 0;
        double y2 = 0;
        double a = 0;
        double f = 0;
        double p = 0;
        double kar = 0;
        double servo1 = 0.2;
        double servo2 = -0.2;




        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("hello driver of:", "AEsthetic Robotics Team", "если что-то не работает делал програме НЕ Персман");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // крутим колеса в POV режиме
            //в этом режиме левый стик двигает робота по плоскости не вращая робота по оси, а правый отвечает за поворот корпуса.
            double spin = gamepad1.right_stick_x; //обявляем переменую мощности моторов при вращении корпуса
            double lift = gamepad2.right_stick_y; //объявляем переменную мощности лифт
            if (Math.abs(spin) > 0.01) {
                robot.frontRightMotor.setPower(-spin * p);//right
                robot.frontLeftMotor.setPower(spin * p);//left
                robot.backRightMotor.setPower(-spin * p);//right
                robot.backLeftMotor.setPower(spin * p);//left
            } else {
                if (gamepad1.right_bumper) {   //переключение скорости через множитель
                    p = 0.3; //множитель

                } else {

                    p = 1;//множитель
                }
                y1 = gamepad1.left_stick_y;//считываем значение стика по оси y
                x1 = gamepad1.left_stick_x;//считывание значения стика по оси x


                y2 = y1 * cos60 + x1 * sin30;//формула для механумов
                x2 = x1 * cos60 - y1 * sin30;//формула для механумов
                robot.frontRightMotor.setPower(y2 * p);//right //включение мотора с мощностью значения геймпада
                robot.frontLeftMotor.setPower(x2 * p);//left  //включение мотора с мощностью значения геймпада
                robot.backRightMotor.setPower(x2 * p);//right  //включение мотора с мощностью значения геймпада
                robot.backLeftMotor.setPower(y2 * p);//left  //включение мотора с мощностью значения геймпада

            }

            robot.up.setPower(-0.3*lift);
            robot.zahv.setPower(lift);
            robot.karusel.setPower(kar);


            if (gamepad2.y) {
                f = -1; //переменная мощности мотора спинера
            }
            else {
                f = 0; //переменная мощности мотора спинера
            }
            if (gamepad2.x) {
                a = 0.8;
            } else {
                a = 0;
            }
            if (gamepad2.a) {
                kar = 0.05;
            } else {
                kar = 0;
            }
            if (gamepad2.b){
                robot.zahvat.setPosition(servo1);
            }
            else{
                robot.zahvat.setPosition(servo2);
            }
        }

    }


    // Pace this loop so jaw action is reasonable speed.
    //sleep(3);
}







