document.addEventListener("DOMContentLoaded", () => {
  const loginScreen = document.getElementById("login-screen");
  const alunoDashboard = document.getElementById("aluno-dashboard");
  const professorDashboard = document.getElementById("professor-dashboard");
  const loginForm = document.getElementById("login-form");

  const viewProfFromAluno = document.getElementById("view-prof-from-aluno");
  const viewAlunoFromProf = document.getElementById("view-aluno-from-prof");
  const logoutAluno = document.getElementById("logout-aluno");
  const logoutProf = document.getElementById("logout-prof");

  window.modalOverlay = document.getElementById("modal-overlay");
  window.inscricaoModal = document.getElementById("inscricao-modal");
  window.feedbackModal = document.getElementById("feedback-modal");
  window.feedbackModalTitle = document.getElementById("feedback-modal-title");

  function showScreen(screenToShow) {
    loginScreen.classList.remove("active");
    alunoDashboard.classList.remove("active");
    professorDashboard.classList.remove("active");
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
    } else {
      showScreen(alunoDashboard);
    }
  });

  viewProfFromAluno.addEventListener("click", () =>
    showScreen(professorDashboard)
  );
  viewAlunoFromProf.addEventListener("click", () => showScreen(alunoDashboard));

  function logout() {
    loginForm.reset();
    showScreen(loginScreen);
  }
  logoutAluno.addEventListener("click", logout);
  logoutProf.addEventListener("click", logout);

  window.showInscricaoModal = () => {
    modalOverlay.classList.remove("hidden");
    inscricaoModal.classList.remove("hidden");
  };
  window.showFeedbackModal = (disciplina) => {
    feedbackModalTitle.textContent = `Enviar Feedback para ${disciplina}`;
    modalOverlay.classList.remove("hidden");
    feedbackModal.classList.remove("hidden");
  };
  window.hideModals = () => {
    modalOverlay.classList.add("hidden");
    inscricaoModal.classList.add("hidden");
    feedbackModal.classList.add("hidden");
  };

  showScreen(loginScreen);
});
