import { axiosInstance } from "../../../api/_base/axios-instance.api";

export function useCadastro() {
  async function cadastro({ nome, email, senha, imagem }) {
    const response = await axiosInstance.post(
      "/usuarios",
      {
        nome,
        email,
        senha,
        imagem
      }
    );
    return response.data;
  }

  return { cadastro };
}
