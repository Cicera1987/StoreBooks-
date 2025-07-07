# 📚 StoreBooks

**StoreBooks** é um app Android para gerenciar sua biblioteca pessoal de forma simples e eficiente. Cadastre, edite, exclua e acompanhe o status de leitura dos seus livros com estilo.

<div align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9-blueviolet?logo=kotlin" />
  <img src="https://img.shields.io/badge/MVVM-Architecture-blue" />
  <img src="https://img.shields.io/badge/Room-Database-green" />
  <img src="https://img.shields.io/badge/ViewBinding-enabled-brightgreen" />
</div>

---

## ✨ Funcionalidades

- ✅ Listagem de livros com título, autor e status (📕 Não lido, 📖 Lendo, ✅ Lido)
- ✍️ Cadastro e edição de livros
- 🖼️ Tela de detalhes, na qual posso alterar status e favoritar um livro
- 🗑️ Exclusão direta pelo item
- ⭐ Marcar como favorito
- 🔍 Busca por nome do livro
- 📦 Armazenamento local com **Room**
- 💡 Interface reativa com **LiveData** e **ViewModel**

---

## 🧱 Tecnologias usadas

| Camada | Tecnologias |
|-------|-------------|
| UI    | ViewBinding, RecyclerView, Material Design |
| Lógica | ViewModel, LiveData |
| Persistência | Room (SQLite) |
| Outros | Kotlin, MVVM, Parcelize |

---

## 🖼️ Layout

📱 A interface foi projetada para ser simples e intuitiva. Veja abaixo um exemplo da tela inicial:

>![Imagem do WhatsApp de 2025-07-07 à(s) 11 00 14_c9a1e9af](https://github.com/user-attachments/assets/c329e1d0-6e47-41bd-b709-4009f1a8e856)
>![Imagem do WhatsApp de 2025-07-07 à(s) 11 00 14_0ce915a2](https://github.com/user-attachments/assets/b48551a0-1f65-4181-b47f-48694803b37c)



---

## 🚀 Como rodar

1. Clone o repositório:
  ```bash
      git clone https://github.com/Cicera1987/StoreBooks.git
  ````


## com.ulbra.storebooks
- data
-  local (Room: entities, dao, database)
-  mapper (conversores entre Entity <-> Model)
-  model (StoreBook)

- ui
-  fragments (FormBookFragment, ListBookFragment)
-  adapter (RecyclerView Adapter + ViewHolder)
-  iewmodels (BookViewModel)

---

💡 Próximas melhorias
- Filtro por status (Lido, Lendo, Não lido)
-  Tela de favoritos
- Exportar backup dos livros
- Dark mode
---

👩‍💻 Desenvolvido por:
- Cícera Ribeiuro
