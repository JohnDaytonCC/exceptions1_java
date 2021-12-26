/* Programa para ler os dados de uma reserva de hotel(n�mero do quarto, data de entrada e data de sa�da
 * e mostrar os dados da reserva, inclusive sua dura��o em dias. Em seguida, ler novas datas de entrada
 * e sa�da, atualizar a reserva, e mostrar novamente a reserva com os dados atualizados.
 * O programa n�o deve aceitar dados inv�lidos para a reserva, conforme as seguintes regras:
 * - Altera��es de reserva s� podem ocorrer para datas futuras
 * - A data de sa�da deve ser maior que a data de entrada
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
			
			Date now = new Date();
			if(checkin.before(now) || checkout.before(now)) {
				System.out.println("Erro in reservation: Reservations dates for update must future dates");
			}
			else if(!checkout.after(checkin)) {
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			}
			else {			
				reservation.updateDates(checkin, checkout);
				System.out.println("Reservation: " + reservation);
			}
		}
		
		input.close();
	}

}
