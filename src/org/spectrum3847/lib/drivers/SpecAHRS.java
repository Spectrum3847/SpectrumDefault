package org.spectrum3847.lib.drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;

public class SpecAHRS extends AHRS {

	public SpecAHRS(Port serial_port_id) {
		super(serial_port_id);
	}

	public SpecAHRS(edu.wpi.first.wpilibj.I2C.Port serial_port_id) {
		super(serial_port_id);
	}

	public SpecAHRS(edu.wpi.first.wpilibj.SerialPort.Port serial_port_id) {
		super(serial_port_id);
	}

	public SpecAHRS(Port i2c_port_id, byte update_rate_hz) {
		super(i2c_port_id, update_rate_hz);
	}

	public SpecAHRS(edu.wpi.first.wpilibj.I2C.Port i2c_port_id, byte update_rate_hz) {
		super(i2c_port_id, update_rate_hz);
	}

	public SpecAHRS(Port spi_port_id, int spi_bitrate, byte update_rate_hz) {
		super(spi_port_id, spi_bitrate, update_rate_hz);
	}

	public SpecAHRS(edu.wpi.first.wpilibj.SerialPort.Port serial_port_id, SerialDataType data_type,
			byte update_rate_hz) {
		super(serial_port_id, data_type, update_rate_hz);
	}

	
}
