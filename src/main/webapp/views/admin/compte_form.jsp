<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion Compte Client</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }

        .container {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #3f51b5;
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .btn {
            margin-top: 20px;
            width: 100%;
            background-color: #3f51b5;
            color: white;
            padding: 12px;
            border: none;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #303f9f;
        }

        .retour {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #607d8b;
            text-decoration: none;
        }

        .retour:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Créer / Modifier un Compte</h2>

    <form action="${pageContext.request.contextPath}/admin/compte/save" method="post">
        <label for="idClient">ID du Client :</label>
        <input type="text" id="idClient" name="idClient" required>

        <label for="numero">Numéro de Compte :</label>
        <input type="text" id="numero" name="numero" required>

        <label for="solde">Solde (DH) :</label>
        <input type="number" step="0.01" id="solde" name="solde" required>

        <label for="devise">Devise :</label>
        <select name="devise" id="devise" required>
            <option value="DH">DH</option>
            <option value="EUR">EUR</option>
            <option value="USD">USD</option>
            <option value="GBP">GBP</option>
        </select>

        <button type="submit" class="btn">💾 Enregistrer le compte</button>
    </form>

    <a href="${pageContext.request.contextPath}/admin/dashboard" class="retour">← Retour à l’accueil admin</a>
</div>

</body>
</html>
