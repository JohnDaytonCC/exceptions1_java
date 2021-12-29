/* Programa para ler os dados de uma reserva de hotel(número do quarto, data de entrada e data de saída
 * e mostrar os dados da reserva, inclusive sua duração em dias. Em seguida, ler novas datas de entrada
 * e saída, atualizar a reserva, e mostrar novamente a reserva com os dados atualizados.
 * O programa não deve aceitar dados inválidos para a reserva, conforme as seguintes regras:
 * - Alterações de reserva só podem ocorrer para datas futuras
 * - A data de saída deve ser maior que a data de entrada
 */

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = input.nextInt();
		System.out.print("Chek-in date (dd/MM/yyyy): ");
		Date checkin = sdf.parse(input.next());
		System.out.print("Chek-out date (dd/MM/yyyy): ");
		Date checkout = sdf.parse(input.next());
		
		if(!checkout.after(checkin)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		}
		else {
			Reservation reservation = new Reservation(number, checkin, checkout);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Chek-in date (dd/MM/yyyy): ");
			checkin = sdf.parse(input.next());
			System.out.print("Chek-out date (dd/MM/yyyy): ");
			checkout = sdf.parse(input.next());
			
			String error = reservation.updateDates(checkin, checkout);
			if(error != null) {
				System.out.println("Error in reservation: " + error);
			}
			else {
				System.out.println("Reservation: " + reservation);
			}
		}
		
		input.close();
	}

}
