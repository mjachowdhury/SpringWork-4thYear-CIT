/**
 * 
 */
package ie.mohammed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohammed
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
	private int customerId; 
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String contactNumber;
	private String email;

}
