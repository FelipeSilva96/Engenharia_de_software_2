document.addEventListener("DOMContentLoaded", () => {
  const loginScreen = document.getElementById("login-screen");
  const alunoDashboard = document.getElementById("aluno-dashboard");
  const professorDashboard = document.getElementById("professor-dashboard");
  const loginForm = document.getElementById("login-form");

  const logoutAluno = document.getElementById("logout-aluno");
  const logoutProf = document.getElementById("logout-prof");

  window.modalOverlay = document.getElementById("modal-overlay");
  window.inscricaoModal = document.getElementById("inscricao-modal");
  window.feedbackModal = document.getElementById("feedback-modal");
  window.viewFeedbackModal = document.getElementById("view-feedback-modal");
  window.createClassModal = document.getElementById("create-class-modal");

  const feedbackInput = document.getElementById("comentario");
  const feedbackListContainer = document.getElementById(
    "feedback-list-container"
  );
  const feedbackModalTitle = document.getElementById("feedback-modal-title");
  const viewFeedbackTitle = document.getElementById("view-feedback-title");

  const btnEnviarFeedback = document.querySelector(
    "#feedback-modal .btn-primary"
  );
  const btnConfirmarInscricao = document.querySelector(
    "#inscricao-modal .btn-primary"
  );
  const inputCodigoTurma = document.getElementById("codigo-turma-input");

  const btnAbrirCriarTurma = document.querySelector(
    "#professor-dashboard .actions .btn-primary"
  );
  const btnConfirmarCriarTurma = document.getElementById(
    "btn-confirm-create-class"
  );
  const inputNomeTurma = document.getElementById("new-class-name");
  const inputCodTurma = document.getElementById("new-class-code");
  const gridProfessor = document.querySelector(
    "#professor-dashboard .card-grid"
  );

  let turmaAtual = "";

  function showScreen(screenToShow) {
    document
      .querySelectorAll(".screen")
      .forEach((s) => s.classList.remove("active"));
    screenToShow.classList.add("active");
  }

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const email = document.getElementById("email").value;

    if (
      email.toLowerCase().includes("professor") ||
      email.toLowerCase().includes("ana.costa")
    ) {
      showScreen(professorDashboard);
      carregarTurmasProfessor();
    } else {
      showScreen(alunoDashboard);
    }
  });

  function logout() {
    loginForm.reset();
    showScreen(loginScreen);
  }
  logoutAluno.addEventListener("click", logout);
  logoutProf.addEventListener("click", logout);

  window.showInscricaoModal = () => {
    inputCodigoTurma.value = "";
    modalOverlay.classList.remove("hidden");
    inscricaoModal.classList.remove("hidden");
  };

  window.showFeedbackModal = (disciplina) => {
    turmaAtual = disciplina;
    feedbackModalTitle.textContent = `Enviar Feedback para ${disciplina}`;
    feedbackInput.value = "";
    modalOverlay.classList.remove("hidden");
    feedbackModal.classList.remove("hidden");
  };

  if (btnAbrirCriarTurma) {
    btnAbrirCriarTurma.addEventListener("click", () => {
      inputNomeTurma.value = "";
      inputCodTurma.value = "";
      modalOverlay.classList.remove("hidden");
      createClassModal.classList.remove("hidden");
    });
  }

  window.hideModals = () => {
    modalOverlay.classList.add("hidden");
    document
      .querySelectorAll(".modal")
      .forEach((m) => m.classList.add("hidden"));
  };

  if (btnConfirmarCriarTurma) {
    btnConfirmarCriarTurma.addEventListener("click", () => {
      const nome = inputNomeTurma.value.trim();
      const codigo = inputCodTurma.value.trim();

      if (!nome || !codigo) {
        alert("Preencha todos os campos.");
        return;
      }

      const novaTurma = { nome, codigo, alunos: 0 };
      let turmasSalvas = JSON.parse(
        localStorage.getItem("turmas_prof_db") || "[]"
      );
      turmasSalvas.push(novaTurma);
      localStorage.setItem("turmas_prof_db", JSON.stringify(turmasSalvas));

      adicionarCardProfessor(novaTurma);
      alert("Turma criada com sucesso!");
      hideModals();
    });
  }

  function adicionarCardProfessor(turma) {
    const card = document.createElement("div");
    card.className = "card";
    card.innerHTML = `
        <h3>${turma.nome}</h3>
        <p class="class-code">Código: ${turma.codigo}</p>
        <p class="student-count">${turma.alunos} Alunos</p>
        <button class="btn btn-view-feedback">Ver Feedbacks</button>
      `;

    card
      .querySelector(".btn-view-feedback")
      .addEventListener("click", () => abrirVerFeedbacks(turma.nome));
    gridProfessor.appendChild(card);
  }

  function carregarTurmasProfessor() {
    if (document.getElementById("turmas-carregadas-flag")) return;
    const turmas = JSON.parse(localStorage.getItem("turmas_prof_db") || "[]");
    turmas.forEach((t) => adicionarCardProfessor(t));
    const flag = document.createElement("input");
    flag.type = "hidden";
    flag.id = "turmas-carregadas-flag";
    gridProfessor.appendChild(flag);
  }

  btnConfirmarInscricao.addEventListener("click", () => {
    const codigo = inputCodigoTurma.value.trim().toUpperCase();
    if (!codigo) {
      alert("Digite o código.");
      return;
    }

    let nomeDisciplina = "Disciplina (" + codigo + ")";
    let nomeProfessor = "Prof. Ana Costa";

    if (codigo === "TCC00456") nomeDisciplina = "Banco de Dados";

    const turmasProf = JSON.parse(
      localStorage.getItem("turmas_prof_db") || "[]"
    );
    const turmaEncontrada = turmasProf.find(
      (t) => t.codigo.toUpperCase() === codigo
    );
    if (turmaEncontrada) {
      nomeDisciplina = turmaEncontrada.nome;
      nomeProfessor = "Você (Professor)";
    }

    const gridAluno = document.querySelector("#aluno-dashboard .card-grid");
    const novoCard = document.createElement("div");
    novoCard.className = "card";
    novoCard.innerHTML = `
        <h3>${nomeDisciplina}</h3>
        <p class="prof-name">${nomeProfessor}</p>
        <button class="btn btn-feedback" onclick="showFeedbackModal('${nomeDisciplina}')">✔ Enviar Feedback</button>
      `;
    gridAluno.appendChild(novoCard);

    alert(`Inscrição realizada em: ${nomeDisciplina}!`);
    hideModals();
  });

  btnEnviarFeedback.addEventListener("click", () => {
    const texto = feedbackInput.value;
    if (texto.length < 15) {
      alert("Mínimo 15 caracteres.");
      return;
    }

    const novoFeedback = {
      texto: texto,
      data: new Date().toLocaleDateString(),
      classificacao: document.getElementById("classificacao").value,
      turma: turmaAtual,
    };

    let feedbacksSalvos = JSON.parse(
      localStorage.getItem("feedbacks_db") || "[]"
    );
    feedbacksSalvos.push(novoFeedback);
    localStorage.setItem("feedbacks_db", JSON.stringify(feedbacksSalvos));

    alert("Feedback enviado com sucesso!");
    hideModals();
  });

  window.abrirVerFeedbacks = function (nomeTurma) {
    if (!nomeTurma || typeof nomeTurma !== "string") {
      console.error("Erro: Nome da turma inválido ao abrir feedbacks");
      return;
    }

    if (viewFeedbackTitle)
      viewFeedbackTitle.textContent = `Feedbacks: ${nomeTurma}`;

    let feedbacksSalvos = JSON.parse(
      localStorage.getItem("feedbacks_db") || "[]"
    );

    const feedbacksDaTurma = feedbacksSalvos.filter(
      (fb) => fb.turma === nomeTurma
    );

    feedbackListContainer.innerHTML = "";

    if (feedbacksDaTurma.length === 0) {
      feedbackListContainer.innerHTML =
        '<p style="color: #666; font-style: italic;">Nenhum feedback recebido para esta turma ainda.</p>';
    } else {
      feedbacksDaTurma.forEach((fb) => {
        const div = document.createElement("div");
        div.style.borderBottom = "1px solid #eee";
        div.style.padding = "10px 0";
        div.innerHTML = `
                <strong>Nota: ${fb.classificacao}/5</strong> <span style="font-size:0.8rem; color:#888">(${fb.data})</span><br>
                <p style="margin: 5px 0;">"${fb.texto}"</p>
              `;
        feedbackListContainer.appendChild(div);
      });
    }
    modalOverlay.classList.remove("hidden");
    viewFeedbackModal.classList.remove("hidden");
  };

  showScreen(loginScreen);
});
