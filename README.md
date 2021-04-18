# mobile-av1
Repositório para a avaliação da primeira unidade parte 1 da disciplina de desenvolvimento Mobile no Cesar School


- Crie um projeto Android do zero, sem se basear em nenhuma branch apresentada em sala
- Na activity principal, apresente uma lista de Frutas, com foto, nome, um pequeno texto com os benefícios da fruta
- A lista deve ser criada usando RecyclerView.
- Na Activity principal deve existir um FloatingButton, com o símbolo de "+". Ao clicar nesse botão, deve-se abrir uma SecondActivity for result, que contém: Um EditText para o nome da Fruta, Um EditText(Multiline) para os benefícios, e um botão de Adicionar Imagem, que deve enviar um intent implícito para adquirir a imagem da galeria.
- Na SecondActivity, ao clicar em concluir, ela deve enviar o objeto Parcelable Fruta de volta para a Activity principal. Que adicionar uma fruta na lista
- Ao clicar na imagem da fruta, devera abrir outra activity, DetalhesActivity.
- Que contém os detalhes da fruta (Nome, foto, beneficios) e contém a opção Remover, que ao clicada, remove a fruta da listagem e volta para a MainAcitivity
- Caso o celular seja rotacionado a listagem não deve se perder
