package br.com.case

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {
    @Id
    var id: Int? = null
}
