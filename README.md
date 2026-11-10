# Proffy Mobile - Fluxo de Recuperação de Senha

## Aluno
- **Nome:** Rodrigo Valentino
- **Turma:** Sistemas de Informação - 6° Período

## Implementação da Validação e Navegação

A validação da tela de recuperação de senha (`ForgotPasswordActivity`)
foi implementada utilizando `addTextChangedListener` e `setOnFocusChangeListener` 
no campo de e-mail (`TextInputEditText`). 
A cada alteração no texto ou saída do campo, a função `validateEmail` 
verifica se o campo não está vazio e se o e-mail é válido 
utilizando `Patterns.EMAIL_ADDRESS`. Se inválido, o `TextInputLayout` 
exibe a mensagem de erro correspondente. 
Quando o e-mail é válido, o erro é removido e o 
botão tem seu estado alterado para verde habilitado com o 
texto "Enviar". Ao clicar no botão habilitado, 
a navegação para a `FinallyActivity` é realizada através de uma `Intent` 
que envia o título e o subtítulo do fluxo como extras. 
Por fim, na `FinallyActivity`, o botão "Voltar ao login" 
reinicia a `LoginActivity` utilizando as flags `FLAG_ACTIVITY_NEW_TASK` 
e `FLAG_ACTIVITY_CLEAR_TASK` para limpar toda a pilha de navegação.