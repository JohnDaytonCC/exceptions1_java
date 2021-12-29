package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	Reservation(){
	}

	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservations dates for update must future dates");
		}
		if(!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNuber() {
		return roomNumber;
	}

	public void setRoomNuber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkin, Date checkout) {
		
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservations dates for update must future dates");
		}
		if(!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		/*
		StringBuilder sb = new StringBuilder();
		sb.append("Room "+ roomNumber);
		sb.append(", check-in: " + sdf.format(checkin));
		sb.append(", check-out: " + sdf.format(checkout));
		sb.append(", " + duration() + " nights");
		return sb.toString();
		*/
		
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkin)
				+ ", check-out: "
				+ sdf.format(checkout)
				+ ", "
				+ duration()
				+ " nights";
	}

}
