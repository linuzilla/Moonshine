package ncu.cc.moonshine.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_rights database table.
 * 
 */
@Entity
@Table(name="role_rights")
public class RoleRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false)
	private Integer sn;

	@Column(nullable=false, length=255)
	private String rights;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false)
	private Role role;

    public RoleRight() {
    }

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}