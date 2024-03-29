package br.com.alura.helloapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alura.helloapp.data.Contato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {
    @Insert(onConflict = REPLACE)
    suspend  fun insere(contato: Contato)

    @Query("SELECT * FROM Contato")
    fun buscaTodos(): Flow<List<Contato>>

    @Query ("SELECT * FROM Contato WHERE id =  :id")
     fun  getById(id: Long): Flow<Contato?>

    @Query ("DELETE FROM Contato where id = :id")
    suspend fun delete(id: Long)
}