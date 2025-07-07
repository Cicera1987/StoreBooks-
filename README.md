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

>![image](https://github.com/user-attachments/assets/3f00c83c-89a6-4b14-bdcb-30e39a9c94c6) ![image](https://github.com/user-attachments/assets/9fab9042-427d-43f0-bbf6-b32e86782f7d) ![image](https://github.com/user-attachments/assets/adea15f1-34e5-4951-89d4-0c225f91dfbf) ![image](https://github.com/user-attachments/assets/754c2618-dc02-45e9-b4eb-e2f049422993) ![image](https://github.com/user-attachments/assets/906a73ca-8a8d-46f5-81e9-a46ff5d0d25f)







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
