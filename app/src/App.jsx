import './App.css'
import { useCadastro } from './hook/api/usuario/use-cadastro.hook'
import { useForm } from './hook/form/use-form.hook';

function App() {
  const { cadastro } = useCadastro();
  const {formData, handleChange} = useForm({
  nome: {value:"", error:"" },
  email: {value:"", error:"" },
  senha: {value:"", error:"" }
});

function getBase64(file) {
  const reader = new FileReader();
  return new Promise((resolve, reject) => {
    reader.readAsDataURL(file);
    reader.onerror = function (error) {
      reject(new DOMException("Problem parsing input file."));
    };
    reader.onload = function() {
      resolve(reader.result);
    };
  });
}
  
  async function handleSubmit(event) {
    event.preventDefault();
    const file = document.getElementById("fileInput").files[0];
    const file64 = await getBase64(file);
    console.log("Resultado: "+file64);

    await cadastro({
      nome: formData.nome.value,
      email: formData.email.value,
      senha: formData.senha.value,
      imagem: file64}
      );
  }


  return (
    <>
    <form onSubmit={handleSubmit} className='teste' encType='multipart/form-data'>
    <input
        name={"nome"}
        value={formData.nome.value}
        onChange={handleChange}
        type={"text"}
      />
      <input
        name={"email"}
        value={formData.email.value}
        onChange={handleChange}
        type={"text"}
      />
      <input
        name={"senha"}
        value={formData.senha.value}
        onChange={handleChange}
        type={"password"}
      />
      <input
        id={"fileInput"}
        name={"file"}
        type={"file"}
      />
      <button>enviar</button>
    </form>
    </>
  )
}

export default App
