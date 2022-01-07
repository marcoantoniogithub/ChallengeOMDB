import com.example.desafiomobile.features.detailsFilm.di.DetailsFilmModule
import com.example.desafiomobile.features.favoriteFilm.di.FavoriteFilmModule
import com.example.desafiomobile.features.listFilm.di.ListFilmModule
import org.koin.dsl.module

object AppModule {
    val instance = module {
        listOf(
            DetailsFilmModule.instance,
            ListFilmModule.instance,
            FavoriteFilmModule.instance
        )
    }
}
