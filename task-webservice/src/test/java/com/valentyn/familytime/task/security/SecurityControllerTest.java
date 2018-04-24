/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2016 (c), by Valentine
 *
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 *
 * @date 2016-25-08 20:18:16 ::
 *
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 *                                                                   *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.security;

import com.valentyn.familytime.task.controller.AbstractRestControllerTest;
import com.valentyn.familytime.task.model.entity.User;
import com.valentyn.familytime.task.model.service.RoleService;
import com.valentyn.familytime.task.model.service.UserService;
import com.valentyn.familytime.task.security.controller.SecurityController;
import com.valentyn.familytime.task.security.model.persistence.mock.RoleMock;
import com.valentyn.familytime.task.security.model.persistence.mock.UserMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Security controller.
 * @see SecurityController
 *
 * @version 1.0
 */
public class SecurityControllerTest extends AbstractRestControllerTest {
    /// *** Properties  *** ///
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @InjectMocks
    private SecurityController securityController = new SecurityController();

    /// *** Methods     *** ///
    /**
     * Init environment for run test.
     * 
     * @throws Exception General application exception
     */
    @Before
    @Override
    public void tearUp() throws Exception {

//        super.tearUp();

        //- Init mocks -//
        MockitoAnnotations.initMocks(this);

        //- Set up application -//
        this.mvc = MockMvcBuilders.standaloneSetup( securityController )
            .apply(documentationConfiguration(this.restDocumentation))
            .build();
    }

    /**
     * Test for sign up a new user.
     *
     * @throws Exception    General Exception of application.
     */

    @Test
    public void testSignUpActionSuccess() throws Exception {
        //- Mock service -//
        when(this.roleService.findByAuthority( any() ) ).thenReturn( RoleMock.findByAuthority() );
        when( this.userService.create( any( User.class ) ) ).thenReturn( UserMock.createParent() );
        when( this.passwordEncoder.encode( anyString() ) ).thenReturn( "Te$t" );

        //- Do Sign Up request -//
        this.mvc.perform(
            post( "/security/signup" )
                .contentType( MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                        "\"familyName\": \"test\", " +
                        "\"firstName\": \"Unit\", " +
                        "\"lastName\": \"test\", " +
                        "\"middleName\": \"test\", " +
                        "\"username\": \"test@gmail.com\", " +
                        "\"password\": \"Te$tTe$tTe$t\", " +
                        "\"birthday\": \"2000-08-10\", " +
                        "\"gender\": false, " +
                        "\"locale\": \"en_US\"" +
                        "}"
                )
        )
            .andExpect( status().isCreated() ).andDo(print())
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$.roles[0].authority", notNullValue() ) )
            .andDo(
                document(
                    "signup-example",
                    requestFields(
                        fieldWithPath("familyName").description("Name of new family."),
                        fieldWithPath("firstName").description("First name of new user."),
                        fieldWithPath("lastName").description("Last name of new user."),
                        fieldWithPath("middleName").description("Middle name of new user."),
                        fieldWithPath("username").description("E-mail of new user."),
                        fieldWithPath("password").description("Password of new user."),
                        fieldWithPath("birthday").description("Birthday of new user."),
                        fieldWithPath("gender").description("Gender of new user."),
                        fieldWithPath("locale").description("Locale of new user.")

                    )
                )
            );
    }
}
