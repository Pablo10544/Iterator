package iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    @Test
    void playlistVaziaNaoPossuiProximoElemento() {
        Iterator<String> iterator = new Playlist().iterator();

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void percorreMusicasNaOrdemDeInsercao() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        playlist.adicionar("Asa Branca");
        Iterator<String> iterator = playlist.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("Aquarela", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Asa Branca", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertFalse(iterator.hasNext());
    }

    @Test
    void consultarProximoElementoNaoAvancaPosicao() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        Iterator<String> iterator = playlist.iterator();

        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals("Aquarela", iterator.next());
    }

    @Test
    void permiteObterElementoSemConsultarHasNext() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");

        assertEquals("Aquarela", playlist.iterator().next());
    }

    @Test
    void iteradoresPossuemPosicoesIndependentes() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        playlist.adicionar("Asa Branca");
        Iterator<String> primeiro = playlist.iterator();
        Iterator<String> segundo = playlist.iterator();

        assertEquals("Aquarela", primeiro.next());
        assertEquals("Asa Branca", primeiro.next());
        assertEquals("Aquarela", segundo.next());
        assertFalse(primeiro.hasNext());
        assertTrue(segundo.hasNext());
    }

    @Test
    void permitePercorrerComForEach() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        playlist.adicionar("Asa Branca");
        List<String> percorridas = new ArrayList<>();

        for (String musica : playlist) {
            percorridas.add(musica);
        }

        assertEquals(Arrays.asList("Aquarela", "Asa Branca"), percorridas);
    }

    @Test
    void preservaMusicasRepetidas() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        playlist.adicionar("Aquarela");
        List<String> percorridas = new ArrayList<>();

        playlist.iterator().forEachRemaining(percorridas::add);

        assertEquals(Arrays.asList("Aquarela", "Aquarela"), percorridas);
    }

    @Test
    void alteracoesNaPlaylistAparecemApenasEmNovosIteradores() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        Iterator<String> anterior = playlist.iterator();

        playlist.adicionar("Asa Branca");
        List<String> atuais = new ArrayList<>();
        playlist.iterator().forEachRemaining(atuais::add);

        assertEquals("Aquarela", anterior.next());
        assertFalse(anterior.hasNext());
        assertEquals(Arrays.asList("Aquarela", "Asa Branca"), atuais);
    }

    @Test
    void naoPermiteRemoverPeloIterador() {
        Playlist playlist = new Playlist();
        playlist.adicionar("Aquarela");
        Iterator<String> iterator = playlist.iterator();
        iterator.next();

        assertThrows(UnsupportedOperationException.class, iterator::remove);
        assertEquals("Aquarela", playlist.iterator().next());
    }

    @Test
    void rejeitaMusicaNula() {
        Playlist playlist = new Playlist();

        assertThrows(NullPointerException.class, () -> playlist.adicionar(null));
        assertFalse(playlist.iterator().hasNext());
    }
}
