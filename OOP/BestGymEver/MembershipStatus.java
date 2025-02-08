/*
* Klass enum GymMember
*
* Ylva Fröjmark
* 2024-10-17
*/

package BestGymEver;

public enum MembershipStatus {
    MEMBER("medlem"),
    EX_MEMBER("inte längre medlem"),
    NOT_MEMBER("inte medlem");

    public final String string;

    private MembershipStatus(String string) {
        this.string = string;
    }
}
