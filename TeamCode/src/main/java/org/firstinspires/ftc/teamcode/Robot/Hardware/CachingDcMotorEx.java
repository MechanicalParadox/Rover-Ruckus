package org.firstinspires.ftc.teamcode.Robot.Hardware;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class CachingDcMotorEx implements DcMotorEx, CachingMotor {

    private DcMotorEx delegate;
    private double cachedPower = 0;
    private double cachedVelocity = 0;
    private boolean needsPowerUpdate = false;
    private boolean needsVelocityUpdate = false;
    private AngleUnit angleUnit = AngleUnit.RADIANS;

    public CachingDcMotorEx(DcMotorEx motorEx) {
        delegate = motorEx;
    }

    @Override
    public synchronized void setDirection(Direction direction) {
        delegate.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return delegate.getDirection();
    }

    @Override
    public void setPower(double power) {
        synchronized (this) {
            if (power != cachedPower) {
                cachedPower = power;
                needsPowerUpdate = true;
            }
        }
    }

    @Override
    public synchronized double getPower() {
        return delegate.getPower();
    }

    @Override
    public synchronized void update() {
        synchronized (this) {
            if (needsPowerUpdate) delegate.setPower(cachedPower);
            if (needsVelocityUpdate) delegate.setVelocity(cachedVelocity, angleUnit);
        }
    }

    @Override
    public synchronized void setMotorEnable() {
        delegate.setMotorEnable();
    }

    @Override
    public synchronized void setMotorDisable() {
        delegate.setMotorDisable();
    }

    @Override
    public synchronized boolean isMotorEnabled() {
        return delegate.isMotorEnabled();
    }

    @Override
    public synchronized void setVelocity(double angularRate) {
        this.setVelocity(angularRate, AngleUnit.RADIANS);
    }

    @Override
    public void setVelocity(double angularRate, AngleUnit unit) {
        synchronized (this) {
            if (angularRate != cachedVelocity) {
                needsVelocityUpdate = true;
                cachedVelocity = angularRate;
                this.angleUnit = unit;
            }
        }
    }

    @Override
    public synchronized double getVelocity() {
        return delegate.getVelocity();
    }

    @Override
    public synchronized double getVelocity(AngleUnit unit) {
        return delegate.getVelocity(unit);
    }

    @Override
    @Deprecated
    public synchronized void setPIDCoefficients(RunMode mode, PIDCoefficients pidCoefficients) {
        delegate.setPIDCoefficients(mode, pidCoefficients);
    }

    @Override
    public synchronized void setPIDFCoefficients(RunMode mode, PIDFCoefficients pidfCoefficients) throws UnsupportedOperationException {
        delegate.setPIDFCoefficients(mode, pidfCoefficients);
    }

    @Override
    public synchronized void setVelocityPIDFCoefficients(double p, double i, double d, double f) {
        delegate.setVelocityPIDFCoefficients(p, i, d, f);
    }

    @Override
    public synchronized void setPositionPIDFCoefficients(double p) {
        delegate.setPositionPIDFCoefficients(p);
    }

    @Override
    @Deprecated
    public synchronized PIDCoefficients getPIDCoefficients(RunMode mode) {
        return delegate.getPIDCoefficients(mode);
    }

    @Override
    public synchronized PIDFCoefficients getPIDFCoefficients(RunMode mode) {
        return delegate.getPIDFCoefficients(mode);
    }

    @Override
    public synchronized void setTargetPositionTolerance(int tolerance) {
        delegate.setTargetPositionTolerance(tolerance);
    }

    @Override
    public synchronized int getTargetPositionTolerance() {
        return delegate.getTargetPositionTolerance();
    }

    @Override
    public synchronized MotorConfigurationType getMotorType() {
        return delegate.getMotorType();
    }

    @Override
    public synchronized void setMotorType(MotorConfigurationType motorType) {
        delegate.setMotorType(motorType);
    }

    @Override
    public synchronized DcMotorController getController() {
        return delegate.getController();
    }

    @Override
    public synchronized int getPortNumber() {
        return delegate.getPortNumber();
    }

    @Override
    public synchronized void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        delegate.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public synchronized ZeroPowerBehavior getZeroPowerBehavior() {
        return delegate.getZeroPowerBehavior();
    }

    @Override
    @Deprecated
    public synchronized void setPowerFloat() {
        delegate.setPowerFloat();
    }

    @Override
    public synchronized boolean getPowerFloat() {
        return delegate.getPowerFloat();
    }

    @Override
    public synchronized void setTargetPosition(int position) {
        delegate.setTargetPosition(position);
    }

    @Override
    public synchronized int getTargetPosition() {
        return delegate.getTargetPosition();
    }

    @Override
    public synchronized boolean isBusy() {
        return delegate.isBusy();
    }

    @Override
    public synchronized int getCurrentPosition() {
        return delegate.getCurrentPosition();
    }

    @Override
    public synchronized void setMode(RunMode mode) {
        delegate.setMode(mode);
    }

    @Override
    public synchronized RunMode getMode() {
        return delegate.getMode();
    }

    @Override
    public synchronized Manufacturer getManufacturer() {
        return delegate.getManufacturer();
    }

    @Override
    public synchronized String getDeviceName() {
        return delegate.getDeviceName();
    }

    @Override
    public synchronized String getConnectionInfo() {
        return delegate.getConnectionInfo();
    }

    @Override
    public synchronized int getVersion() {
        return delegate.getVersion();
    }

    @Override
    public synchronized void resetDeviceConfigurationForOpMode() {
        delegate.resetDeviceConfigurationForOpMode();
    }

    @Override
    public synchronized void close() {
        delegate.close();
    }
}