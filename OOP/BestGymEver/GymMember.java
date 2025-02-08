/*
* Klass GymMember
*
* Ylva Fröjmark
* 2024-10-17
*
* Instansvariabler:
* private String socialSecurityNumber
* private String name;
* private LocalDate payedLast;
* private MembershipStatus status <- enum
*
* Metoder:
* Konstruktor GymMember(String socialSecurityNumber, String name, String payedLast) <-omvandlar sträng till LocalDate
* void setMembershipStatus() <- sätter enum beroende LocalDate payedLast
* void payMemberShip(String payDate) <- registrerar ny årsbetalning och få ny membershipstatus
* Getters för name, socialSecurityNumber och status
 */

package BestGymEver;
import java.time.LocalDate;

public class GymMember{

    private String socialSecurityNumber;
    private String name;
    private LocalDate payedLast;
    private MembershipStatus status;

    public GymMember(String socialSecurityNumber, String name, String payedLast) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.payedLast = LocalDate.parse(payedLast);
        this.setMembershipStatus();
    }
    public void setMembershipStatus() {
        if (this.payedLast.isAfter(LocalDate.now().minusYears(1))) {
            this.status = MembershipStatus.MEMBER;
        }else {
            this.status = MembershipStatus.EX_MEMBER;
        }
    }
    public void payMemberShip(String payDate) {
        this.payedLast = LocalDate.parse(payDate);
        this.setMembershipStatus();
    }
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public String getName() {
        return name;
    }
    public MembershipStatus getStatus() {
        return status;
    }
}
